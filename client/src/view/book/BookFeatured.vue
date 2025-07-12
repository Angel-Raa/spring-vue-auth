<script lang="ts" setup>
import type { Book } from '@/types';

defineProps<{
  featuredBooks?: Book[]
}>()
</script>
<template>
  <div class="bg-white py-16">
    <div class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
      <div class="mx-auto max-w-2xl text-center">
        <h2 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">Featured Books</h2>
        <p class="mt-4 text-lg leading-8 text-gray-600">
          Check out some of our most popular and recently added books
        </p>
      </div>
      <div
        class="mx-auto mt-16 grid max-w-2xl grid-cols-1 gap-x-8 gap-y-12 sm:grid-cols-2 lg:mx-0 lg:max-w-none lg:grid-cols-3"
      >
        <article
          v-for="book in featuredBooks"
          :key="book.bookId"
          class="group relative overflow-hidden rounded-lg bg-white shadow-sm border hover:shadow-lg transition-all duration-300 hover:-translate-y-1"
        >
          <div class="aspect-[3/4] overflow-hidden bg-gray-100">
            <img
              :src="book.image"
              :alt="book.title"
              class="h-full w-full object-cover group-hover:scale-105 transition-transform duration-300"
            />
            <div class="absolute top-3 right-3">
              <span
                :class="[
                  'inline-flex items-center rounded-full px-2.5 py-0.5 text-xs font-medium',
                  book.available
                    ? 'bg-green-100 text-green-800 ring-1 ring-inset ring-green-600/20'
                    : 'bg-red-100 text-red-800 ring-1 ring-inset ring-red-600/20',
                ]"
              >
                {{ book.available ? 'Available' : 'Unavailable' }}
              </span>
            </div>
          </div>
          <div class="p-6">
            <h3
              class="text-lg font-semibold text-gray-900 group-hover:text-teal-600 transition-colors duration-200"
            >
              {{ book.title }}
            </h3>
            <p class="mt-2 text-sm text-gray-600">by {{ book.author }}</p>
            <div class="mt-4">
              <RouterLink
                :to="{ name: 'Book' }"
                class="text-sm font-medium text-teal-600 hover:text-teal-500 transition-colors duration-200"
              >
                View Details →
              </RouterLink>
            </div>
          </div>
        </article>
      </div>
      <div class="mt-12 text-center">
        <RouterLink
          to="/book"
          class="inline-flex items-center rounded-md bg-white px-6 py-3 text-base font-semibold text-teal-600 shadow-sm ring-1 ring-inset ring-teal-600 hover:bg-teal-50 transition-colors duration-200"
        >
          View All Books
          <svg class="ml-2 h-5 w-5" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
            <path
              fill-rule="evenodd"
              d="M7.21 14.77a.75.75 0 01.02-1.06L11.168 10 7.23 6.29a.75.75 0 111.04-1.08l4.5 4.25a.75.75 0 010 1.08l-4.5 4.25a.75.75 0 01-1.06-.02z"
              clip-rule="evenodd"
            />
          </svg>
        </RouterLink>
      </div>
    </div>
  </div>
</template>
<style></style>
