import type { ApiResponse, Book, PaginatedResponse } from '@/types'

const API_BASE_URL = 'http://localhost:8080/api/v1/books'
export const getAllBooks = async ({
  initialPage = 1,
  size = 10,
}: {
  initialPage: number
  size: number
}): Promise<Book[]> => {
  const url = `${API_BASE_URL}?page=${initialPage}&size=${size}`
  try {
    const res = await fetch(url)
    if (!res.ok) {
      throw new Error('Error ' + res.status)
    }
    const response: ApiResponse<PaginatedResponse<Book>> = await res.json()
    console.log(response.data.content)

    return response.data.content
  } catch (error) {
    console.error('Error fetching books:', error)
    throw new Error('Error al obtener los libros')
  }
}

export const getBySlug = async (slug: string): Promise<Book> => {
  try {
    const res = await fetch(`${API_BASE_URL}/slug/${slug}`)
    if (!res.ok) {
      throw new Error('Error ' + res.status)
    }

    const response: ApiResponse<Book> = await res.json()
    return response.data
  } catch (error) {
    console.error('Error fetching books:', error)
    throw new Error('Error al obtener los libros')
  }
}
