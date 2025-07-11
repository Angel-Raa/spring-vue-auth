<script lang="ts" setup>
import type { Book } from '@/types'

defineProps<{
  data: Book[]
}>()
</script>
<template>
  <RouterLink
    v-for="book in data"
    :key="book.bookId"
    :to="{ name: 'BookDetails', params: { slug: book.slug } }"
    custom
    v-slot="{ navigate, href }"
  >
    <article
      :href="href"
      @click="navigate"
      class="group relative overflow-hidden rounded-lg bg-white shadow-sm border hover:shadow-md transition-all duration-200 hover:-translate-y-1 cursor-pointer"
    >
      <!-- Book Image -->
      <div class="aspect-[3/4] overflow-hidden bg-gray-100">
        <img
          :src="book.image || '/placeholder.svg?height=400&width=300'"
          :alt="book.title"
          class="h-full w-full object-cover group-hover:scale-105 transition-transform duration-200"
        />
        <div class="absolute top-3 right-3">
          <span
            :class="[
              'inline-flex items-center rounded-full px-2 py-1 text-xs font-medium',
              book.available ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800',
            ]"
          >
            {{ book.available ? 'Available' : 'Unavailable' }}
          </span>
        </div>
      </div>

      <!-- Book Details -->
      <div class="p-4">
        <h3
          class="font-semibold text-gray-900 line-clamp-2 group-hover:text-amber-600 transition-colors"
        >
          {{ book.title }}
        </h3>
        <p class="mt-1 text-sm text-gray-600">by {{ book.author }}</p>
        <div class="mt-3 flex items-center justify-between text-xs text-gray-500">
          <span>{{ new Date(book.publicationYear).getFullYear() }}</span>
          <span class="font-mono">{{ book.isbn }}</span>
        </div>
      </div>

      <!-- Hover Overlay -->
      <div class="absolute inset-0 bg-black/0 group-hover:bg-black/5 transition-colors duration-200"></div>
    </article>
  </RouterLink>
</template>

<style></style>
