import { useState, useEffect, ChangeEvent } from "react";
import { useDispatch, useSelector } from "react-redux";

import * as S from "./styles";

import { Botao, BotaoSalvar } from "../../styles";
import { ContactType } from "../../features/contacts/contactType";
import {
  editContact,
  removeContact,
  setIsEditing,
} from "../../features/contacts/contactSlice";

type ContactCardProps = {
  contact: ContactType;
  isEditing: boolean;
};

const ContactCard = ({ contact, isEditing }: ContactCardProps) => {
  const dispatch = useDispatch();

  const [contactName, setContactName] = useState(contact.name);
  const [contactEmail, setContactEmail] = useState(contact.email);
  const [contactTel, setContactTel] = useState(contact.telephone);

  const toggleEdit = (bool: boolean) => {
    dispatch(setIsEditing({ id: contact.id, is: bool }));
  };

  const editContactCard = () => {
    dispatch(
      editContact({
        name: contactName || "",
        email: contactEmail || "",
        telephone: contactTel || "",
        id: contact.id,
      })
    );
    toggleEdit(false);
  };

  const cancelEditing = () => {
    setContactName(contact?.name);
    setContactEmail(contact?.email);
    setContactTel(contact?.telephone);
    toggleEdit(false);
  };

  return (
    <S.Card>
      <S.Titulo
        disabled={!isEditing}
        value={contactName}
        type="text"
        onChange={(evento) => setContactName(evento.target.value)}
      />
      <S.Descricao
        disabled={!isEditing}
        value={contactEmail}
        type="email"
        onChange={(evento) => setContactEmail(evento.target.value)}
      />
      <S.Descricao
        disabled={!isEditing}
        value={contactTel}
        type="tel"
        onChange={(evento) => setContactTel(evento.target.value)}
      />
      <S.BarraAcoes>
        {isEditing ? (
          <>
            <BotaoSalvar onClick={editContactCard}>Salvar</BotaoSalvar>
            <S.BotaoCancelarRemover onClick={cancelEditing}>
              Cancelar
            </S.BotaoCancelarRemover>
          </>
        ) : (
          <>
            <Botao onClick={() => toggleEdit(true)}>Editar</Botao>
            <S.BotaoCancelarRemover
              onClick={() => dispatch(removeContact(contact))}
            >
              Remover
            </S.BotaoCancelarRemover>
          </>
        )}
      </S.BarraAcoes>
    </S.Card>
  );
};

export default ContactCard;
