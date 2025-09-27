import { configureStore } from "@reduxjs/toolkit";
import contactSliceReducer from "./features/contacts/contactSlice";

const store = configureStore({
  reducer: {
    contacts: contactSliceReducer,
  },
});

export type RootReducer = ReturnType<typeof store.getState>;

export default store;
