import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { ContactType } from "./contactType";

type ContactEntry = {
  entry: ContactType;
  isEditing: boolean;
};

type ContactSliceType = {
  contacts: ContactEntry[];
};

const initialState: ContactSliceType = {
  contacts: [
    {
      entry: {
        name: "John Mary",
        telephone: "8889-9888",
        email: "johnmary@johnmary.com",
        id: 1,
      },
      isEditing: false,
    },
    {
      entry: {
        name: "Joseff Mary",
        telephone: "6661-9666",
        email: "joseffmary@joseffmary.com",
        id: 2,
      },
      isEditing: false,
    },
  ],
};

const contactSlice = createSlice({
  name: "contacts",
  initialState,
  reducers: {
    addContact: (state, action: PayloadAction<ContactType>) => {
      if (
        !state.contacts.some((contact) => contact.entry.id == action.payload.id)
      ) {
        state.contacts = [
          ...state.contacts,
          { entry: action.payload, isEditing: false },
        ];
      }
    },
    removeContact: (state, action: PayloadAction<ContactType>) => {
      state.contacts = state.contacts.filter(
        (contact) => contact.entry.id != action.payload?.id
      );
    },
    editContact: (state, action: PayloadAction<ContactType>) => {
      const contactIndex = state.contacts.findIndex((contact) => {
        return contact.entry.id == action.payload.id;
      });

      state.contacts[contactIndex].entry = action.payload;
    },
    setIsEditing: (
      state,
      action: PayloadAction<{ id: number; is: boolean }>
    ) => {
      const contactIndex = state.contacts.findIndex((contact) => {
        return contact.entry.id == action.payload.id;
      });

      state.contacts[contactIndex].isEditing = action.payload.is;
    },
  },
});

export const { addContact, removeContact, editContact, setIsEditing } =
  contactSlice.actions;
export default contactSlice.reducer;
