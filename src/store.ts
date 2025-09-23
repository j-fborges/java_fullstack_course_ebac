import { configureStore } from '@reduxjs/toolkit'
import cartSliceReducer from './components/Header/cartSlice'
import api from './services/api'

const store = configureStore({
  reducer: {
    cartSliceReducer,
    [api.reducerPath]: api.reducer
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(api.middleware)
})

export type RootReducer = ReturnType<typeof store.getState>

export default store

export const { getState, subscribe, dispatch } = store
