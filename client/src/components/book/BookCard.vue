<script lang="ts" setup>
import type { Book } from '@/types'

defineProps<{
  data: Book
  isFavorite: boolean
  showFullDescription: boolean
  formattedYear: string | number
  description: string
  shortDescription: boolean | string
  toggleFavorite: () => void
  borrowBook: () => void
  reserveBook: () => void
}>()

</script>
<template>
  <div class="mx-auto max-w-7xl px-4 py-8 sm:px-6 lg:px-8">
    <div class="lg:grid lg:grid-cols-2 lg:gap-x-8 lg:items-start">
      <!-- Book Image -->
      <div class="flex flex-col-reverse">
        <div class="aspect-[3/4] overflow-hidden rounded-lg bg-gray-100 shadow-lg">
          <img
            :src="data.image || '/placeholder.svg?height=600&width=450'"
            :alt="data.title"
            class="h-full w-full object-cover object-center"
          />
        </div>
      </div>

      <!-- Book Information -->
      <div class="mt-10 px-4 sm:px-0 sm:mt-16 lg:mt-0">
        <div class="flex items-start justify-between">
          <div class="flex-1">
            <h1 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">
              {{ data.title }}
            </h1>
            <p class="text-xl text-gray-600 mt-2">
              by <span class="font-semibold text-gray-900">{{ data.author }}</span>
            </p>
          </div>
          <button
            @click="toggleFavorite"
            :class="[
              'ml-4 p-2 rounded-full transition-colors duration-200',
              isFavorite
                ? 'text-red-500 hover:text-red-600 bg-red-50'
                : 'text-gray-400 hover:text-red-500 hover:bg-red-50',
            ]"
          >
            <svg class="h-6 w-6" fill="currentColor" viewBox="0 0 24 24">
              <path
                d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"
              />
            </svg>
          </button>
        </div>

        <!-- Availability Status -->
        <div class="mt-6">
          <div class="flex items-center">
            <span
              :class="[
                'inline-flex items-center rounded-full px-3 py-1 text-sm font-medium',
                data.available
                  ? 'bg-green-100 text-green-800 ring-1 ring-inset ring-green-600/20'
                  : 'bg-red-100 text-red-800 ring-1 ring-inset ring-red-600/20',
              ]"
            >
              <svg
                :class="['mr-1.5 h-2 w-2', data.available ? 'fill-green-400' : 'fill-red-400']"
                viewBox="0 0 6 6"
                aria-hidden="true"
              >
                <circle cx="3" cy="3" r="3" />
              </svg>
              {{ data.available ? 'Available for borrowing' : 'Currently unavailable' }}
            </span>
          </div>
        </div>

        <!-- Book Details -->
        <div class="mt-8">
          <h3 class="text-lg font-medium text-gray-900">Book Details</h3>
          <div class="mt-4 space-y-4">
            <div class="flex justify-between py-2 border-b border-gray-200">
              <span class="text-sm font-medium text-gray-500">ISBN</span>
              <span class="text-sm text-gray-900 font-mono">{{ data.isbn }}</span>
            </div>
            <div class="flex justify-between py-2 border-b border-gray-200">
              <span class="text-sm font-medium text-gray-500">Publication Year</span>
              <span class="text-sm text-gray-900">{{ formattedYear }}</span>
            </div>
            <div class="flex justify-between py-2 border-b border-gray-200">
              <span class="text-sm font-medium text-gray-500">Author</span>
              <span class="text-sm text-gray-900">{{ data.author }}</span>
            </div>
            <div class="flex justify-between py-2 border-b border-gray-200">
              <span class="text-sm font-medium text-gray-500">Book ID</span>
              <span class="text-sm text-gray-900 font-mono">{{ data.bookId }}</span>
            </div>
            <div class="flex justify-between py-2 border-b border-gray-200">
              <span class="text-sm font-medium text-gray-500">Slug</span>
              <span class="text-sm text-gray-900 font-mono">{{ data.slug }}</span>
            </div>
          </div>
        </div>

        <!-- Description -->
        <div class="mt-8">
          <h3 class="text-lg font-medium text-gray-900">Description</h3>
          <div class="mt-4">
            <p class="text-sm text-gray-600 leading-relaxed">
              {{ showFullDescription ? description : shortDescription }}
            </p>
            <button
              @click="showFullDescription = !showFullDescription"
              class="mt-2 text-sm font-medium text-teal-600 hover:text-teal-500 transition-colors duration-200"
            >
              {{ showFullDescription ? 'Show less' : 'Read more' }}
            </button>
          </div>
        </div>

        <!-- Action Buttons -->
        <div class="mt-10 flex flex-col sm:flex-row gap-4">
          <button
            v-if="data.available"
            @click="borrowBook"
            class="flex-1 bg-teal-600 border border-transparent rounded-md py-3 px-8 flex items-center justify-center text-base font-medium text-white hover:bg-teal-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-teal-500 transition-colors duration-200"
          >
            <svg
              class="mr-2 h-5 w-5"
              fill="none"
              viewBox="0 0 24 24"
              stroke-width="1.5"
              stroke="currentColor"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                d="M12 6v6h4.5m4.5 0a9 9 0 11-18 0 9 9 0 0118 0z"
              />
            </svg>
            Borrow Book
          </button>
          <button
            v-else
            @click="reserveBook"
            class="flex-1 bg-gray-600 border border-transparent rounded-md py-3 px-8 flex items-center justify-center text-base font-medium text-white hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500 transition-colors duration-200"
          >
            <svg
              class="mr-2 h-5 w-5"
              fill="none"
              viewBox="0 0 24 24"
              stroke-width="1.5"
              stroke="currentColor"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                d="M12 6v6h4.5m4.5 0a9 9 0 11-18 0 9 9 0 0118 0z"
              />
            </svg>
            Reserve Book
          </button>
          <button
            class="flex-1 sm:flex-initial bg-white border border-gray-300 rounded-md py-3 px-8 flex items-center justify-center text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-teal-500 transition-colors duration-200"
          >
            <svg
              class="mr-2 h-5 w-5"
              fill="none"
              viewBox="0 0 24 24"
              stroke-width="1.5"
              stroke="currentColor"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                d="M7.217 10.907a2.25 2.25 0 100 2.186m0-2.186c.18.324.283.696.283 1.093s-.103.77-.283 1.093m0-2.186l9.566-5.314m-9.566 7.5l9.566 5.314m0 0a2.25 2.25 0 103.935 2.186 2.25 2.25 0 00-3.935-2.186zm0-12.814a2.25 2.25 0 103.935-2.186 2.25 2.25 0 00-3.935 2.186z"
              />
            </svg>
            Share
          </button>
        </div>

        <!-- Additional Information -->
        <div class="mt-8 border-t border-gray-200 pt-8">
          <h3 class="text-lg font-medium text-gray-900">Additional Information</h3>
          <div class="mt-4 prose prose-sm text-gray-600">
            <ul class="space-y-2">
              <li class="flex items-center">
                <svg class="mr-2 h-4 w-4 text-green-500" fill="currentColor" viewBox="0 0 20 20">
                  <path
                    fill-rule="evenodd"
                    d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z"
                    clip-rule="evenodd"
                  />
                </svg>
                Free borrowing for library members
              </li>
              <li class="flex items-center">
                <svg class="mr-2 h-4 w-4 text-green-500" fill="currentColor" viewBox="0 0 20 20">
                  <path
                    fill-rule="evenodd"
                    d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z"
                    clip-rule="evenodd"
                  />
                </svg>
                14-day borrowing period
              </li>
              <li class="flex items-center">
                <svg class="mr-2 h-4 w-4 text-green-500" fill="currentColor" viewBox="0 0 20 20">
                  <path
                    fill-rule="evenodd"
                    d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z"
                    clip-rule="evenodd"
                  />
                </svg>
                Renewable once if no reservations
              </li>
              <li class="flex items-center">
                <svg class="mr-2 h-4 w-4 text-green-500" fill="currentColor" viewBox="0 0 20 20">
                  <path
                    fill-rule="evenodd"
                    d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z"
                    clip-rule="evenodd"
                  />
                </svg>
                Digital copy available for members
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <!-- Related Books Section -->
    <div class="mt-16 border-t border-gray-200 pt-16">
      <h2 class="text-2xl font-bold tracking-tight text-gray-900">You might also like</h2>
      <div class="mt-6 grid grid-cols-1 gap-y-10 gap-x-6 sm:grid-cols-2 lg:grid-cols-4 xl:gap-x-8">
        <div class="group relative bg-white rounded-lg shadow-sm border p-4">
          <div class="aspect-[3/4] bg-gray-100 rounded-md mb-4">
            <div class="h-full w-full bg-gray-200 rounded-md flex items-center justify-center">
              <span class="text-gray-400 text-sm">Related Book</span>
            </div>
          </div>
          <h3 class="text-sm font-medium text-gray-900">Similar Book Title</h3>
          <p class="text-sm text-gray-600">by Author Name</p>
        </div>
      </div>
    </div>
  </div>
</template>
<style></style>
