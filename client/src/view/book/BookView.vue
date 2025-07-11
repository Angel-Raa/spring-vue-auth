<script lang="ts" setup>
import BooksGrid from '@/components/book/BooksGrid.vue'
import LoadingComponent from '@/components/shared/LoadingComponent.vue'
import PaginationComponent from '@/components/shared/PaginationComponent.vue'
import { useBooks } from '@/hook/useBooks'

interface Book {
  bookId: string
  title: string
  author: string
  isbn: string
  slug: string
  image: string
  publicationYear: Date
  available: boolean
}

const { data, error, isLoading, nextPage, prePage } = useBooks({
  initialPage: 1,
  initialSize: 10,
})
</script>

<template>
  <div class="min-h-screen bg-gray-50">
    <!-- Header -->
      <div class="mx-auto max-w-7xl px-4 py-6 sm:px-6 lg:px-8">
        <h1 class="text-5xl font-bold tracking-tight text-gray-900 sm:text-4xl">📚 Book Library</h1>
      </div>


    <main class="mx-auto max-w-7xl px-4 py-8 sm:px-6 lg:px-8">
      <!-- Loading State -->
      <<LoadingComponent v-if="isLoading" message="Cargando libros..." :isLoading />

      <!-- Error State -->
      <div v-else-if="error" class="rounded-lg bg-red-50 p-4 border border-red-200">
        <div class="flex">
          <div class="flex-shrink-0">
            <svg class="h-5 w-5 text-red-400" viewBox="0 0 20 20" fill="currentColor">
              <path
                fill-rule="evenodd"
                d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.28 7.22a.75.75 0 00-1.06 1.06L8.94 10l-1.72 1.72a.75.75 0 101.06 1.06L10 11.06l1.72 1.72a.75.75 0 101.06-1.06L11.06 10l1.72-1.72a.75.75 0 00-1.06-1.06L10 8.94 8.28 7.22z"
                clip-rule="evenodd"
              />
            </svg>
          </div>
          <div class="ml-3">
            <h3 class="text-sm font-medium text-red-800">Error loading books</h3>
            <p class="mt-1 text-sm text-red-700">{{ error }}</p>
          </div>
        </div>
      </div>

      <!-- Books Grid -->
      <div v-else-if="data && data.length > 0" class="space-y-6">
        <!-- Stats -->
        <div class="rounded-lg bg-white p-6 shadow-sm border">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-sm font-medium text-gray-600">Total Books</p>
              <p class="text-2xl font-bold text-gray-900">{{ data.length }}</p>
            </div>
            <div class="rounded-full bg-amber-100 p-3">
              <svg
                class="h-6 w-6 text-amber-600"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  d="M12 6.042A8.967 8.967 0 006 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 016 18c2.305 0 4.408.867 6 2.292m0-14.25A8.966 8.966 0 0118 3.75c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0118 18a8.967 8.967 0 00-6 2.292m0-14.25v14.25"
                />
              </svg>
            </div>
          </div>
        </div>

        <!-- Books Grid -->
        <div class="grid gap-6 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
          <BooksGrid :data="data" />
        </div>

        <!-- Pagination -->
        <PaginationComponent :next-page="nextPage" :pre-page="prePage" :data="data" />
      </div>
      <!--  -->
      <!-- Empty State -->
      <div v-else class="text-center py-12">
        <svg
          class="mx-auto h-12 w-12 text-gray-400"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
          aria-hidden="true"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M12 6.253v13m0-13C10.832 5.477 9.246 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.754 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.754 5 16.5 5c1.746 0 3.332.477 4.5 1.253v13C19.832 18.477 18.246 18 16.5 18c-1.746 0-3.332.477-4.5 1.253"
          />
        </svg>
        <h3 class="mt-2 text-sm font-semibold text-gray-900">No se encontraron libros</h3>
        <p class="mt-1 text-sm text-gray-500">Comience agregando algunos libros a su biblioteca.</p>
      </div>
    </main>
  </div>
</template>

<style scoped>
.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
