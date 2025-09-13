import Header from './components/Cabecalho/index'
import Hero from './components/Hero/index'
import ListaVagas from './containers/ListaVagas/index'

import './global.css'

function App() {
  return (
    <>
      <Header />
      <Hero />
      <div className="container">
        <ListaVagas />
      </div>
    </>
  )
}

export default App
