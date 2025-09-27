import { useSelector } from "react-redux";
import { MainContainer, Titulo } from "../../styles";
import Contact from "../ContactCard";

import { RootReducer } from "../../store";

const ContactList = () => {
  const { contacts } = useSelector((state: RootReducer) => state.contacts);

  return (
    <MainContainer>
      <Titulo as="p">
        Contact Listing, {`${contacts.length} contacts found:`}
      </Titulo>
      <ul>
        {contacts.map((contact) => (
          <li key={contact.entry.id}>
            <Contact contact={contact.entry} isEditing={contact.isEditing} />
          </li>
        ))}
      </ul>
    </MainContainer>
  );
};

export default ContactList;
