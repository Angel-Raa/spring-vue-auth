<script lang="ts" setup>
import { ref, computed } from 'vue'
import { useSlug } from '@/hook/useSlug'
import { useRoute } from 'vue-router'
import type { Book } from '@/types'
import BookCard from '@/components/book/BookCard.vue'
import NotFoundBookDetail from '@/components/book/NotFoundBookDetail.vue'

const route = useRoute()
const slug = typeof route.params.slug === 'string' ? route.params.slug : route.params.slug[0]

const { data, isLoading } = useSlug(slug)
console.log(data.value)

const isFavorite = ref(false)
const showFullDescription = ref(false)

const getDescription = (
  book: Book,
) => `This is a comprehensive book that explores various themes and concepts. The author ${book.author} has crafted a compelling narrative that engages readers from the very first page. Published in ${new Date(book.publicationYear).getFullYear()}, this work has become a significant contribution to its field.

The book delves deep into complex topics while maintaining accessibility for readers of all backgrounds. With its unique perspective and thorough research, it offers valuable insights that continue to resonate with audiences today.`

const formattedYear = computed(() =>
  data.value ? new Date(data.value.publicationYear).getFullYear() : '',
)
const description = computed(() => (data.value ? getDescription(data.value) : ''))
const shortDescription = computed(() => description.value.slice(0, 200) + '...')

const toggleFavorite = () => {
  isFavorite.value = !isFavorite.value
}

const goBack = () => {
  window.history.back()
}

const borrowBook = () => {
  if (data.value) {
    console.log('Borrowing book:', data.value.title)
  }
}

const reserveBook = () => {
  if (data.value) {
    console.log('Reserving book:', data.value.title)
  }
}
</script>

<template>
  <div class="min-h-screen bg-gray-50">
    <!-- Loading State -->
    <div v-if="isLoading" class="flex items-center justify-center min-h-screen">
      <div class="flex items-center space-x-2">
        <div
          class="h-8 w-8 animate-spin rounded-full border-4 border-teal-500 border-t-transparent"
        ></div>
        <span class="text-lg font-medium text-gray-700">Loading book details...</span>
      </div>
    </div>

    <!-- Book Details Content -->
    <div v-else-if="data" class="min-h-screen">
      <!-- Breadcrumb Navigation -->
      <div class="bg-white border-b border-gray-200">
        <div class="mx-auto max-w-7xl px-4 py-4 sm:px-6 lg:px-8">
          <nav class="flex" aria-label="Breadcrumb">
            <ol class="flex items-center space-x-4">
              <li>
                <div>
                  <button
                    @click="goBack"
                    class="text-gray-400 hover:text-gray-500 transition-colors duration-200"
                  >
                    <svg
                      class="h-5 w-5 flex-shrink-0"
                      viewBox="0 0 20 20"
                      fill="currentColor"
                      aria-hidden="true"
                    >
                      <path
                        fill-rule="evenodd"
                        d="M9.707 14.707a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 1.414L7.414 9H15a1 1 0 110 2H7.414l2.293 2.293a1 1 0 010 1.414z"
                        clip-rule="evenodd"
                      />
                    </svg>
                    <span class="sr-only">Back</span>
                  </button>
                </div>
              </li>
              <li>
                <div class="flex items-center">
                  <svg
                    class="h-5 w-5 flex-shrink-0 text-gray-300"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                    aria-hidden="true"
                  >
                    <path
                      fill-rule="evenodd"
                      d="M7.21 14.77a.75.75 0 01.02-1.06L11.168 10 7.23 6.29a.75.75 0 111.04-1.08l4.5 4.25a.75.75 0 010 1.08l-4.5 4.25a.75.75 0 01-1.06-.02z"
                      clip-rule="evenodd"
                    />
                  </svg>
                  <RouterLink
                    to="/book"
                    class="ml-4 text-sm font-medium text-gray-500 hover:text-gray-700 transition-colors duration-200"
                  >
                    Books
                  </RouterLink>
                </div>
              </li>
              <li>
                <div class="flex items-center">
                  <svg
                    class="h-5 w-5 flex-shrink-0 text-gray-300"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                    aria-hidden="true"
                  >
                    <path
                      fill-rule="evenodd"
                      d="M7.21 14.77a.75.75 0 01.02-1.06L11.168 10 7.23 6.29a.75.75 0 111.04-1.08l4.5 4.25a.75.75 0 010 1.08l-4.5 4.25a.75.75 0 01-1.06-.02z"
                      clip-rule="evenodd"
                    />
                  </svg>
                  <span class="ml-4 text-sm font-medium text-gray-500 truncate max-w-xs">{{
                    data.title
                  }}</span>
                </div>
              </li>
            </ol>
          </nav>
        </div>
      </div>

      <!-- Main Content -->
      <BookCard
        :data="data"
        :isFavorite="isFavorite"
        :showFullDescription="showFullDescription"
        :formattedYear="formattedYear"
        :description="description"
        :shortDescription="shortDescription"
        :toggleFavorite="toggleFavorite"
        :borrowBook="borrowBook"
        :reserveBook="reserveBook"
      />
    </div>

    <!-- Error State -->
    <NotFoundBookDetail v-else />
    <!--END ERROR STATE -->
  </div>
</template>

<style scoped>
.prose ul {
  list-style: none;
  padding-left: 0;
}
</style>
