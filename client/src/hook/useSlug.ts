import { getBySlug } from '@/API/book'
import { useQuery } from '@tanstack/vue-query'

export const useSlug = (slug: string) => {
  const { data, isLoading, error } = useQuery({
    queryKey: ['slug', slug],
    queryFn: () => getBySlug(slug),
    enabled: !!slug,
    retry: false,
  })
  return {
    data,
    isLoading,
    error,
  }
}
