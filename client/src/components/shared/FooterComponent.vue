<script lang="ts" setup>
import { computed } from 'vue'

const currentYear = computed(() => new Date().getFullYear())
</script>

<template>
  <footer class="bg-gray-50 dark:bg-gray-900 border-t border-gray-200 dark:border-gray-800">
    <div class="max-w-5xl mx-auto px-4 py-8 sm:px-6 lg:px-8">
      <div class="flex flex-col md:flex-row justify-between items-start md:items-center gap-6">
        <!-- Branding -->
        <div>
          <h3 class="text-lg font-semibold text-gray-900 dark:text-white mb-2">
            Biblioteca Digital
          </h3>
          <p class="text-sm text-gray-600 dark:text-gray-400 max-w-sm leading-relaxed">
            Explora libros, autores y géneros en una plataforma sencilla y clara.
          </p>
        </div>

        <!-- Enlaces simples -->
        <ul class="flex flex-col sm:flex-row gap-4 text-sm text-gray-600 dark:text-gray-400">
          <li><RouterLink :to="{ name: 'Book' }" class="hover:underline">Libros</RouterLink></li>
          <li><RouterLink to="/autores" class="hover:underline">Autores</RouterLink></li>
          <li><RouterLink to="/generos" class="hover:underline">Géneros</RouterLink></li>
          <li><RouterLink to="/contacto" class="hover:underline">Contacto</RouterLink></li>
        </ul>
      </div>

      <div class="mt-6 border-t border-gray-200 dark:border-gray-700 pt-4 text-center">
        <p class="text-xs text-gray-500 dark:text-gray-500">
          © {{ currentYear }} Biblioteca Digital. Todos los derechos reservados.
        </p>
      </div>
    </div>
  </footer>
</template>
<style>
</style>
