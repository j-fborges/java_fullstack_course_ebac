import ContactList from "./components/ContactList";
import Sidebar from "./components/Sidebar";
import EstiloGlobal, { AppContainer } from "./styles";

const App = () => {
  return (
    <div>
      <EstiloGlobal />
        <AppContainer>
          <Sidebar/>
          <ContactList />
        </AppContainer>
    </div>
  );
};

export default App;
