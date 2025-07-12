<script lang="ts" setup>
import HomeHero from '@/components/home/HomeHero.vue'
import HomeStats from '@/components/home/HomeStats.vue'
import { useBooks } from '@/hook/useBooks'
import { ref } from 'vue'
import BookFeatured from '../book/BookFeatured.vue'
import HomeFeatures from '@/components/home/HomeFeatures.vue'
import HomeCta from '@/components/home/HomeCta.vue'

// Estadísticas simuladas (puedes conectar con tu API)
const stats = ref([
  { name: 'Total Books', value: '2,847', icon: 'book' },
  { name: 'Available Now', value: '1,923', icon: 'check' },
  { name: 'Active Members', value: '456', icon: 'users' },
  { name: 'Books Borrowed', value: '924', icon: 'bookmark' },
])

const { data: featuredBooks, isLoading } = useBooks({ initialPage: 1, initialSize: 3 })
</script>

<template>
  <div class="min-h-screen bg-gray-50">
    <!-- Hero Section -->
    <HomeHero />
    <!-- Stats Section -->
    <HomeStats :stats="stats" />

    <!-- Book Section -->

    <!-- Featured Books Section -->
    <BookFeatured :featuredBooks="featuredBooks" />
    <!-- End  Featured Books Section -->

    <!-- Features Section -->
    <HomeFeatures />
    <!-- CTA Section -->
    <HomeCta />
    <!-- End CTA Section -->
  </div>
</template>

<style scoped>
/* Smooth scroll para el anchor link */
html {
  scroll-behavior: smooth;
}
</style>
