import styled, { createGlobalStyle } from 'styled-components'
import variaveis from './variaveis'

const EstiloGlobal = createGlobalStyle`
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: Roboto, sans-serif;
    list-style: none;
  }
`

export const SideContainer = styled.div`
  display: flex;
  flex-direction: column;
  padding: 0 20px;
`

export const AppContainer = styled.div`
  display: flex;
  flex-direction: row;
  grid-template-columns: 224px auto;

  @media (max-width: 600px) {
    flex-direction: column;
  }
`

export const MainContainer = styled.main`
  padding: 0 40px;
  height: 100vh;
  width: 100%;
  overflow-y: scroll;

  @media (max-width: 800px) {
      padding: 0 20px;
  }
`

export const Titulo = styled.h2`
  display: block;
  margin-top: 40px;
  margin-bottom: 40px;
  font-size: 18px;
  font-weight: bold;
`

export const Campo = styled.input`
  padding: 8px;
  background-color: #fff;
  border-radius: 8px;
  font-weight: bold;
  color: #666666;
  border-color: #666666;
  width: 100%;
  margin-bottom: 8px;
`

export const Botao = styled.button`
  font-weight: bold;
  font-size: 12px;
  color: #fff;
  padding: 8px 12px;
  border: none;
  cursor: pointer;
  background-color: ${variaveis.azulEscuro};
  border-radius: 8px;
  margin-right: 8px;
`

export const BotaoSalvar = styled(Botao)`
  background-color: ${variaveis.verde};
`

export default EstiloGlobal
