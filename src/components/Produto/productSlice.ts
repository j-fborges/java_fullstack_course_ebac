import { createSlice } from '@reduxjs/toolkit'
import { Produto } from '../../App'
import api from '../../services/api'

type productSliceInitialState = {
  product: Produto[]
}

const initialState: productSliceInitialState = {
  product: []
}

const productSlice = createSlice({
  name: 'product',
  initialState,
  reducers: {}
})

export {}
