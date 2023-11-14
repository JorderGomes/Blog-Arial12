"use client";

import Button from "@/components/Button";
import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const ContarPostsPage = () => {
  const [quantidadeDePosts, setQuantidadeDePosts] = useState(0);
  const [loaded, setLoaded] = useState(false);
  const router = useRouter();

  async function fazConsulta() {
    const response = await axios
      .get(`http://localhost:8080/posts/count`)
      .then((response) => response.data)
      .catch((error) => {
        console.log(error);
        return null;
      });

    setLoaded(true);
    if (response === null) {
      return;
    }

    setQuantidadeDePosts(response);
  }

  return (
    <SubPageContainer
      menuTitle="Contagem de quantos posts existem na base de dados"
      returnCallback={() => router.push("/posts")}
    >
      {loaded ? (
        <label className="mb-4" htmlFor="quantidadeDePosts">
          Quantidade de posts: {quantidadeDePosts}
        </label>
      ) : (
        <label className="mb-4" htmlFor="quantidadeDePosts">
          Clique no botão para carregar a contagem
        </label>
      )}

      <Button
        callback={() => {
          if (!loaded) {
            fazConsulta();
          } else {
            alert("Já foi carregado!");
          }
        }}
        textoDoBotao={"Carregar a contagem"}
      ></Button>
    </SubPageContainer>
  );
};

export default ContarPostsPage;
