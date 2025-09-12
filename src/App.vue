<script setup>
  import { reactive } from 'vue';

const state = reactive({
 val1: 0,
 val2: 0,
 result: 0,
})

</script>

<template>
  <div class="flex justify-center items-center mt-24">
    <div class="flex flex-col justify-center items-center p-12 gap-6 bg-[#eee] rounded-3xl w-[100%] max-w-[500px] self-center">
      <h1 class="text-3xl font-extrabold">Calculadora Aritmética</h1>
    
      <div class="flex flex-row">
        <label class="text-lg font-bold" for="val1"> Valor 1:</label>
        <input class="bg-white mx-4" type="number" name="" id="val1" @keyup="e => state.val1 = Number(e.target.value)">
      </div>
      <div class="flex flex-row">
        <label class="text-lg font-bold" for="val2">Valor 2:</label>
        <input class="bg-white mx-4" type="number" name="" id="val2" @keyup="e => state.val2 = Number(e.target.value)">
      </div>
      <div class="flex flex-row gap-4">
        <button class="px-4 py-2 rounded-lg text-white text-lg font-extrabold cursor-pointer bg-[#999] hover:bg-[#ddd]" @click="() => state.result = state.val1 + state.val2" type="button">+</button>
        <button class="px-4 py-2 rounded-lg text-white text-lg font-extrabold cursor-pointer bg-[#999] hover:bg-[#ddd]" @click="() => state.result = state.val1 - state.val2" type="button">-</button>
        <button class="px-4 py-2 rounded-lg text-white text-lg font-extrabold cursor-pointer bg-[#999] hover:bg-[#ddd]" @click="() => state.result = state.val1 * state.val2" type="button">x</button>
        <button class="px-4 py-2 rounded-lg text-white text-lg font-extrabold cursor-pointer bg-[#999] hover:bg-[#ddd]" @click="() => state.result = state.val1 / state.val2" type="button">/</button>
      </div>
      <h2 class="text-lg font-extrabold">Result: {{ state.result }}</h2>
    </div>

  </div>
</template>

<style scoped></style>
