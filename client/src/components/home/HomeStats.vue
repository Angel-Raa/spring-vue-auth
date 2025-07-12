<script lang="ts" setup>
defineProps<{
  stats: { name: string; value: string; icon: string }[]
}>()
</script>
<template>
  <div class="bg-teal-600 py-16">
    <div class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
      <div class="mx-auto max-w-2xl lg:max-w-none">
        <div class="text-center">
          <h2 class="text-3xl font-bold tracking-tight text-white">La biblioteca de un vistazo</h2>
          <p class="mt-4 text-lg leading-8 text-teal-100">
           Explora nuestra creciente colección y comunidad activa de lectores.
          </p>
        </div>
        <dl
          class="mt-16 grid grid-cols-1 gap-0.5 overflow-hidden rounded-2xl text-center sm:grid-cols-2 lg:grid-cols-4"
        >
          <div
            v-for="stat in stats"
            :key="stat.name"
            class="flex flex-col bg-white/5 p-8 backdrop-blur-sm"
          >
            <dt class="text-sm font-semibold leading-6 text-teal-100">{{ stat.name }}</dt>
            <dd class="order-first text-3xl font-bold tracking-tight text-white">
              {{ stat.value }}
            </dd>
          </div>
        </dl>
      </div>
    </div>
  </div>
</template>
<style></style>
