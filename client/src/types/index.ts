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

export interface Book {
  message: string
  data: Data
  status: number
  timestamp: Date
}

export interface Data {
  content: Content[]
  totalElements: number
  number: number
  size: number
}

export interface Content {
  bookId: string
  title: string
  author: string
  isbn: string
  slug: string
  publicationYear: Date
  available: boolean
}

export interface CreateBook {
  title: string
  author: string
  isbn: string
  publicationYear: Date | string
  available: boolean
}

export interface UpdateBook extends Partial<CreateBook> {
  bookId: string
}


export interface NavigationItem {
  to:string,
  label:string
  name:string
}
