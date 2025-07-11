import { getAllBooks } from '@/API/book'
import { keepPreviousData, useQuery } from '@tanstack/vue-query'
import { ref } from 'vue'

export const useBooks = ({ initialPage = 1, initialSize =20 }: { initialPage: number; initialSize: number }) => {
  const page = ref(initialPage)
  const size = ref(initialSize)
  const { data, isLoading, error, isPlaceholderData } = useQuery({
    queryKey: ['books', page, size],
    queryFn: () => getAllBooks({ initialPage: page.value, size:size.value }),
    placeholderData: keepPreviousData,

  })

  const prePage = () => {
    page.value = Math.max(page.value - 1, 1)
  }
  const nextPage = () => {
    if (!isPlaceholderData.value) {
      page.value = page.value + 1
    }
  }
  return {
    data,
    isLoading,
    error,
    isPlaceholderData,
    nextPage,
    prePage,
  }
}
