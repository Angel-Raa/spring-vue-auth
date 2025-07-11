<script lang="ts" setup>
defineProps<{
  isLoading: boolean
  message: string
  error?: string | null
}>()
</script>
<template>
  <div v-if="isLoading" class="flex items-center justify-center py-12">
    <div class="flex items-center space-x-2">
      <div
        class="h-8 w-8 animate-spin rounded-full border-4 border-amber-500 border-t-transparent"
      ></div>
      <span class="text-lg font-medium text-gray-700">{{ message }}</span>
    </div>
  </div>
</template>
<style></style>
