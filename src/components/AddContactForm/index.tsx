import { FormEvent, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import { BotaoSalvar, SideContainer, Titulo } from "../../styles";
import { Campo } from "../../styles";
import { Form } from "./styles";
import { addContact } from "../../features/contacts/contactSlice";
import { RootReducer } from "../../store";

const AddContactForm = () => {
  const dispatch = useDispatch();
  const contactsNumber = useSelector(
    (state: RootReducer) => state.contacts.contacts.length
  );

  const [contactName, setContactName] = useState("");
  const [contactEmail, setContactEmail] = useState("");
  const [contactTel, setContactTel] = useState("");

  const registerContact = (evento: FormEvent) => {
    evento.preventDefault();

    dispatch(
      addContact({
        name: contactName,
        email: contactEmail,
        telephone: contactTel,
        id: contactsNumber + 1,
      })
    );
    setContactEmail("");
    setContactName("");
    setContactTel("");
  };

  return (
    <SideContainer>
      <Titulo>Nova tarefa</Titulo>
      <Form onSubmit={registerContact}>
        <Campo
          value={contactName}
          onChange={(evento) => setContactName(evento.target.value)}
          type="text"
          placeholder="Título"
        />
        <Campo
          value={contactEmail}
          onChange={({ target }) => setContactEmail(target.value)}
          type="email"
          placeholder="e-mail"
        />
        <Campo
          value={contactTel}
          onChange={({ target }) => setContactTel(target.value)}
          type="tel"
          placeholder="Telephone"
        />
        <BotaoSalvar type="submit">Cadastrar</BotaoSalvar>
      </Form>
    </SideContainer>
  );
};

export default AddContactForm;
