"use client";

import Button from "@/components/Button";
import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState } from "react";

const ContarPostsCategoriaPage = () => {
  const [categorias, setCategorias] = useState([]);
  const [loaded, setLoaded] = useState(false);
  const [posts, setPosts] = useState(null);
  const router = useRouter();
  const [renderized, setRenderized] = useState(false);

  async function fazConsulta() {
    if (loaded) return;
    const response = await axios
      .get(`http://localhost:8080/posts`)
      .then((response) => response.data)
      .catch((error) => {
        console.log(error);
        return null;
      });

    if (response === null) {
      alert("Nenhum Post foi encontrado!");
    } else {
      alert("Posts carregados com sucesso!");
    }

    setPosts(response);

    setLoaded(true);
  }

  function loadCategorias() {
    if (posts === null) {
      return (
        <p>Não foi possível achar nenhum post para organizar em categorias;</p>
      );
    }

    let tempCategorias = [];
    let tempTableCategorias = [];
    posts.forEach((post) => {
      if (!tempCategorias.includes(post.categoria)) {
        tempCategorias.push(post.categoria);
      }
    });
    tempCategorias.forEach((categoria) => {
      let count = 0;
      posts.forEach((post) => {
        if (post.categoria === categoria) {
          count++;
        }
      });
      tempTableCategorias.push({
        categoria: categoria,
        count,
      });
    });
    setCategorias(tempTableCategorias);
    setRenderized(true);
  }

  return (
    <SubPageContainer
      menuTitle="Contagem de quantos posts existem na base de dados"
      returnCallback={() => router.push("/posts")}
    >
      {loaded ? (
        <>
          {!renderized && loadCategorias()}
          {renderized && (
            <>
              {categorias === null && (
                <p>
                  Não foi possível achar nenhum post para organizar em
                  categorias;
                </p>
              )}
              {categorias !== null && (
                <>
                  {categorias.length === 0 && (
                    <p>
                      Não foi possível achar nenhum post para organizar em
                      categorias;
                    </p>
                  )}
                  {categorias.length !== 0 && (
                    <table
                      className="table table-dark"
                      style={{ width: "60%" }}
                    >
                      <thead>
                        <tr>
                          <th scope="col">Categoria</th>
                          <th scope="col">Quantidade</th>
                        </tr>
                      </thead>
                      <tbody>
                        {categorias.map((categoriaDosPosts, index) => {
                          const { categoria, count } = categoriaDosPosts;
                          return (
                            <tr key={categoria}>
                              <td>{categoria}</td>
                              <td>{count}</td>
                            </tr>
                          );
                        })}
                      </tbody>
                    </table>
                  )}
                </>
              )}
            </>
          )}
        </>
      ) : (
        <>
          <span>Clique no botão para carregar as categorias</span>
        </>
      )}
      <Button
        callback={() => {
          if (!loaded) {
            fazConsulta();
          } else {
            alert("Já foi carregado!");
          }
        }}
        textoDoBotao={"Carregar a categorias"}
      ></Button>
    </SubPageContainer>
  );
};

export default ContarPostsCategoriaPage;
