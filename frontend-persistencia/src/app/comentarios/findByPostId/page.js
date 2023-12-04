/* eslint-disable @next/next/no-async-client-component */
"use client";

import Button from "@/components/Button";
import SubPageContainer from "@/components/SubPageContainer";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState } from "react";

const FindComentarioByIdPage = () => {
  const [id, setId] = useState(0);

  const router = useRouter();

  async function submitHandler(event) {
    event.preventDefault();

    const response = await axios
      .get(`http://localhost:8080/posts/${id}`)
      .then((response) => response.data)
      .catch((error) => {
        console.log(error);
        return null;
      });

    if (response === null) {
      alert("Post não encontrado!");
      return;
    }

    router.push(`/posts/${response.id}/comentarios`);
  }

  function handleChange(event) {
    const { value } = event.target;
    setId(value);
  }

  return (
    <SubPageContainer
      menuTitle="Digite o id do post que deseja ver os comentários:"
      returnCallback={() => router.push(`/comentarios`)}
    >
      <form
        onSubmit={submitHandler}
        style={{ display: "flex", flexDirection: "column" }}
      >
        <label className="form-label" htmlFor="id">
          Id do Post
        </label>
        <input
          className="form-control"
          type="text"
          id="id"
          name="id"
          onChange={handleChange}
          value={id}
        />
        <Button
          callback={() => {}}
          textoDoBotao={"Procurar comentário"}
          type={"submit"}
          className={"mt-3"}
        ></Button>
      </form>
    </SubPageContainer>
  );
};

export default FindComentarioByIdPage;
