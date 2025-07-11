<script setup lang="ts">
import { VueQueryDevtools } from '@tanstack/vue-query-devtools'
import HeaderComponent from './components/shared/HeaderComponent.vue'
import FooterComponent from './components/shared/FooterComponent.vue';
</script>

<template>
  <div class="flex min-h-screen flex-col bg-white  text-gray-800 dark:text-gray-100">
    <HeaderComponent />
    <main class="flex-1 px-4 py-6 sm:px-6 lg:px-8 lg:py-8">
      <RouterView />
      <VueQueryDevtools />
    </main>
    <FooterComponent />
  </div>
</template>
