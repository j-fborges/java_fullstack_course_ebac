<script setup>
import { reactive } from "vue";

const state = reactive({
  val1: 0,
  val2: 0,
  operation: "",
  result: 0,
});

function setResult() {
  const { val1, val2, operation, result } = state;

  switch (operation) {
    case "sum":
      return (state.result = val1 + val2);
    case "sub":
      return (state.result = val1 - val2);
    case "mult":
      return (state.result = val1 * val2);
    case "div":
      return (state.result = val1 / val2);
    default:
      return (state.result = val1 + val2);
  }
}
</script>

<template>
  <div class="flex justify-center items-center mt-24">
    <div
      class="flex flex-col justify-center items-center p-12 gap-6 bg-[#eee] rounded-3xl w-[100%] max-w-[500px] self-center"
    >
      <h1 class="text-3xl font-extrabold">Arithmetics calculator</h1>

      <div class="flex flex-row items-center gap-6">
        <label for="operation" class="text-lg font-bold">Operation: </label>
        <select
        id="operation"
          @change="
            (e) => {
              state.operation = e.target.value;
              setResult()
            }
          "
          class="form-control bg-[#aaa] p-2 pr-4 font-bold border rounded-lg border-black"
        >
          <option value="sum">Sum</option>
          <option value="sub">Subtract</option>
          <option value="mult">Multiply</option>
          <option value="div">Divide</option>
        </select>
      </div>

      <div class="flex flex-row">
        <label class="text-lg font-bold" for="val1"> Value 1:</label>
        <input
          class="bg-white mx-4"
          type="number"
          name=""
          id="val1"
          @keyup="
            (e) => {
              state.val1 = Number(e.target.value);
              setResult();
            }
          "
        />
      </div>
      <div class="flex flex-row">
        <label class="text-lg font-bold" for="val2">Value 2:</label>
        <input
          class="bg-white mx-4"
          type="number"
          name=""
          id="val2"
          @keyup="
            (e) => {
              state.val2 = Number(e.target.value);
              setResult();
            }
          "
        />
      </div>
      <h2 class="text-lg font-extrabold">Result: {{ state.result }}</h2>
    </div>
  </div>
</template>

<style scoped></style>
