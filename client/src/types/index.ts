export interface Auth {
  username: string
  password: string
}
export interface AuthenticationResponse {
  token: string
  type: string
  username: string
  roles: Role[]
  enabled: boolean
  timestamp: Date
}

export interface Role {
  authority: string
}

// types/apiResponse.ts
export interface ApiResponse<T> {
  message: string
  data: T
  status: number
  timestamp: Date
}

export interface PaginatedResponse<T> {
  content: T[]
  totalElements: number
  number: number
  size: number
}

export interface Book {
  bookId: string
  title: string
  author: string
  isbn: string
  slug: string
  image: string
  publicationYear: Date
  available: boolean
}

// Cuando necesites usarlas:
export type BookApiResponse = ApiResponse<PaginatedResponse<Book>>

export interface CreateBook {
  title: string
  author: string
  isbn: string
  publicationYear: Date | string
  available: boolean
  image?: string
}

export interface UpdateBook extends Partial<CreateBook> {
  bookId: string
}

export interface NavigationItem {
  to: string
  label: string
  name: string
}
