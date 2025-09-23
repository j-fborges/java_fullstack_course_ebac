import { useDispatch, useSelector } from 'react-redux'
import { Produto as ProdutoType } from '../App'
import Produto from '../components/Produto'

import * as S from './styles'
import { RootReducer } from '../store'
import { useGetProductsQuery } from '../services/api'
import { addFavorite, addProductToCart } from '../components/Header/cartSlice'

const ProdutosComponent = () => {
  const { isLoading, data: produtos } = useGetProductsQuery()
  const dispatch = useDispatch()

  const favoritos = useSelector(
    (state: RootReducer) => state.cartSliceReducer.favoritos
  )

  if (isLoading) return <h2> Carregando... </h2>

  const produtoEstaNosFavoritos = (produto: ProdutoType) => {
    const produtoId = produto.id
    const IdsDosFavoritos = favoritos.map((f) => f.id)

    return IdsDosFavoritos.includes(produtoId)
  }

  return (
    <>
      <S.Produtos>
        {produtos &&
          produtos.map((produto) => (
            <Produto
              estaNosFavoritos={produtoEstaNosFavoritos(produto)}
              key={produto.id}
              produto={produto}
              favoritar={() => dispatch(addFavorite(produto))}
              aoComprar={() => dispatch(addProductToCart(produto))}
            />
          ))}
      </S.Produtos>
    </>
  )
}

export default ProdutosComponent
