<script lang="ts" setup></script>
<template>
  <div class="bg-white">
    <div class="mx-auto max-w-7xl px-4 py-16 sm:px-6 lg:px-8">
      <div class="text-center">
        <h1 class="text-4xl font-bold tracking-tight text-gray-900 sm:text-5xl md:text-6xl">
          Bienvenidos a Nuestro
          <span class="text-teal-600">Biblioteca</span>
        </h1>
        <p class="mx-auto mt-6 max-w-2xl text-lg leading-8 text-gray-600">
          Descubre miles de libros, explora nuevos mundos y amplía tus conocimientos. Tu próxima
          gran lectura está a un solo clic.
        </p>
        <div class="mt-10 flex items-center justify-center gap-x-6">
          <RouterLink
            :to="{ name: 'Book' }"
            class="rounded-md bg-teal-600 px-6 py-3 text-base font-semibold text-white shadow-sm hover:bg-teal-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-teal-600 transition-colors duration-200"
          >
            Browse Books
          </RouterLink>
          <a
            href="#features"
            class="text-base font-semibold leading-6 text-gray-900 hover:text-teal-600 transition-colors duration-200"
          >
           Más información <span aria-hidden="true">→</span>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>
<style></style>
