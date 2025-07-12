<script lang="ts" setup></script>
<template>
  <
  <div class="bg-white">
    <div class="mx-auto max-w-7xl px-4 py-16 sm:px-6 lg:px-8">
      <div class="mx-auto max-w-2xl text-center">
        <h2 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">
          ¿Listo para comenzar a leer?
        </h2>
        <p class="mx-auto mt-6 max-w-xl text-lg leading-8 text-gray-600">
          Únase a nuestra comunidad de lectores y descubra su próximo libro favorito hoy.
        </p>
        <div class="mt-10 flex items-center justify-center gap-x-6">
          <RouterLink
            :to="{ name: 'Book' }"
            class="rounded-md bg-teal-600 px-6 py-3 text-base font-semibold text-white shadow-sm hover:bg-teal-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-teal-600 transition-colors duration-200"
          >
            Explorar libros
          </RouterLink>
        </div>
      </div>
    </div>
  </div>
</template>
<style></style>
