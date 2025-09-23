import { createSlice, WritableDraft } from '@reduxjs/toolkit'
import { Produto } from '../../App'

type cartSliceInitialState = {
  itensNoCarrinho: Produto[]
  favoritos: Produto[]
}

const initialState: cartSliceInitialState = {
  itensNoCarrinho: [],
  favoritos: []
}

const cartSlice = createSlice({
  name: 'cart',
  initialState,
  reducers: {
    addProductToCart: (state, action) => {
      if (state.itensNoCarrinho.find((p) => p.id === action.payload.id)) {
        alert('Item já adicionado')
      } else {
        state.itensNoCarrinho = [...state.itensNoCarrinho, action.payload]
      }
    },
    addFavorite: (state, action) => {
      if (state.favoritos.find((p) => p.id === action.payload.id)) {
        state.favoritos = state.favoritos.filter(
          (p) => p.id !== action.payload.id
        )
      } else {
        state.favoritos = [...state.favoritos, action.payload]
      }
    }
  }
})
export const { addProductToCart, addFavorite } = cartSlice.actions

export default cartSlice.reducer
