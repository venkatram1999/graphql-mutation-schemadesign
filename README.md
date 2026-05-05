# GraphQL Mutation & Schema Design Patterns

Demonstrating **Input Types**, **Enums**, **Interfaces**, **Unions**, **Inheritance-style Modeling**, and **Structured CRUD Operations**

This project is a Spring Boot GraphQL application for user registration and login.

### 1. CRUD Operations
The project supports basic CRUD operations for users:
- Create user using `register`
- Read all users using `getAllUsers`
- Read one user using `getUserByUsername`
- Update user using `updateUser`
- Delete user using `deleteUser`

### 2. Input Types
Instead of passing many mutation arguments separately, the project uses GraphQL input types:
- `RegisterInput`
- `LoginInput`
- `UpdateUserInput`

This keeps mutations clean and scalable.

### 3. Enums
The project uses enums to restrict valid values:
- `UserRole` → ADMIN, USER, GUEST
- `UserStatus` → ACTIVE, INACTIVE, BLOCKED

### 4. Interface
The `Account` interface is used to define common fields shared by multiple account types:
- id
- username
- role

### 5. Inheritance-style Modeling
The following types implement the `Account` interface:
- `User`
- `Admin`
- `GuestUser`

This makes the schema more reusable and organized.

### 6. Union Types
The project uses union types for authentication results:
- `RegisterResult = AuthSuccess | AuthError`
- `LoginResult = AuthSuccess | AuthError`

This allows a mutation to return either success data or error data in a structured way.

### 7. Authentication Response Design
Instead of returning a plain string, login and register return:
- `AuthSuccess` when the operation succeeds
- `AuthError` when the operation fails

This improves API clarity and error handling.

### 8. GraphQL Queries and Mutations
Queries are used for fetching data.
Mutations are used for changing data such as register, login, update, and delete.

---

## GraphQL Schema Highlights

### Query Operations
- `getAllUsers`
- `getUserByUsername`
- `getAllProfiles`

### Mutation Operations
- `register`
- `login`
- `updateUser`
- `deleteUser`

---

## GraphQL Test Queries and Mutations

### 1. Get All Users

```graphql
query {
  getAllUsers {
    id
    username
    role
    status
  }
}
```

### 2. Get User By Username

```graphql
query {
  getUserByUsername(username: "venkat") {
    id
    username
    role
    status
  }
}
```

### 3. Get All Profiles Using Interface

This query demonstrates the `Account` interface and uses inline fragments to fetch fields from concrete types.

```graphql
query {
  getAllProfiles {
    id
    username
    role

    ... on User {
      status
    }

    ... on Admin {
      adminLevel
    }

    ... on GuestUser {
      visitPurpose
    }
  }
}
```

### 4. Register User

This mutation demonstrates the use of `RegisterInput` and the `RegisterResult` union.

```graphql
mutation {
  register(input: {
    username: "ram"
    password: "ram123"
    role: USER
  }) {
    ... on AuthSuccess {
      message
      user {
        id
        username
        role
        status
      }
    }
    ... on AuthError {
      message
      code
    }
  }
}
```

### 5. Register Duplicate User

```graphql
mutation {
  register(input: {
    username: "venkat"
    password: "venkat123"
    role: USER
  }) {
    ... on AuthSuccess {
      message
      user {
        id
        username
        role
        status
      }
    }
    ... on AuthError {
      message
      code
    }
  }
}
```

### 6. Login User

This mutation demonstrates the use of `LoginInput` and the `LoginResult` union.

```graphql
mutation {
  login(input: {
    username: "venkat"
    password: "venkat123"
  }) {
    ... on AuthSuccess {
      message
      user {
        id
        username
        role
        status
      }
    }
    ... on AuthError {
      message
      code
    }
  }
}
```

### 7. Login With Invalid Password

```graphql
mutation {
  login(input: {
    username: "venkat"
    password: "wrong123"
  }) {
    ... on AuthSuccess {
      message
      user {
        id
        username
      }
    }
    ... on AuthError {
      message
      code
    }
  }
}
```

### 8. Login With Unknown User

```graphql
mutation {
  login(input: {
    username: "unknown"
    password: "test123"
  }) {
    ... on AuthSuccess {
      message
      user {
        id
        username
      }
    }
    ... on AuthError {
      message
      code
    }
  }
}
```

### 9. Update User

This mutation demonstrates the use of `UpdateUserInput`.

```graphql
mutation {
  updateUser(input: {
    username: "venkat"
    newUsername: "venkat_new"
    newPassword: "venkat456"
    role: ADMIN
    status: BLOCKED
  }) {
    id
    username
    role
    status
  }
}
```

### 10. Update User Not Found

```graphql
mutation {
  updateUser(input: {
    username: "unknown"
    newUsername: "unknown_new"
    newPassword: "test123"
    role: USER
    status: ACTIVE
  }) {
    id
    username
    role
    status
  }
}
```

### 11. Delete User

```graphql
mutation {
  deleteUser(username: "ram")
}
```

### 12. Delete User Not Found

```graphql
mutation {
  deleteUser(username: "unknown")
}
```

---
