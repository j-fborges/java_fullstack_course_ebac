import styled from 'styled-components'

export const Aside = styled.aside`
  padding: 8px 0px;
  background-color: #eee;
  height: 100vh;
  max-width: 400px;

  @media (max-width: 600px) {
    max-height: 300px;
    max-width: 100%;
  }
`

export const Filtros = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-gap: 8px;
  margin-top: 16px;
`
