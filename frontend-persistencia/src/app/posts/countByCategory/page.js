"use client";

import Button from "@/components/Button";
import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState } from "react";

const ContarPostsCategoriaPage = () => {
  const [categorias, setCategorias] = useState([]);
  const [loaded, setLoaded] = useState(false);
  const router = useRouter();

  async function fazConsulta() {
    const response = await axios
      .get(`http://localhost:8080/posts/countByCategory`)
      .then((response) => response.data)
      .catch((error) => {
        console.log(error);
        return null;
      });

    setLoaded(true);
    if (response === null) {
      return;
    }

    setCategorias(response);
  }

  function renderCategorias() {
    if (categorias === null) {
      return (
        <p>Não foi possível achar nenhum post para organizar em categorias;</p>
      );
    } else if (categorias.length === 0) {
      return (
        <p>Não foi possível achar nenhum post para organizar em categorias;</p>
      );
    } else {
      return (
        <table className="table table-dark" style={{ width: "60%" }}>
          <thead>
            <tr>
              <th scope="col">Categoria</th>
              <th scope="col">Quantidade</th>
            </tr>
          </thead>
          <tbody>
            {categorias.map((categoria, index) => {
              const categoriaDePost = categoria.categoria;
              const count = categoria["count(id)"];

              return (
                <tr key={categoriaDePost}>
                  <td>{categoriaDePost}</td>
                  <td>{count}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      );
    }
  }

  return (
    <SubPageContainer
      menuTitle="Contagem de quantos posts existem na base de dados"
      returnCallback={() => router.push("/posts")}
    >
      {loaded ? (
        renderCategorias()
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
