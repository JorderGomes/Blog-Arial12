/* eslint-disable @next/next/no-async-client-component */
"use client";

import Button from "@/components/Button";
import SubPageContainer from "@/components/SubPageContainer";
import { useRouter } from "next/navigation";
import { useState } from "react";

const FindUsuarioPage = () => {
  const [id, setId] = useState(0);

  const router = useRouter();

  async function submitHandler(event) {
    event.preventDefault();
    router.push(`/users/${id}`);
  }

  function handleChange(event) {
    const { value } = event.target;
    setId(value);
  }

  return (
    <SubPageContainer
      menuTitle="Digite o id do usuário que deseja procurar:"
      returnCallback={() => router.push("/users")}
    >
      <form
        onSubmit={submitHandler}
        style={{ display: "flex", flexDirection: "column" }}
      >
        <label className="form-label" htmlFor="id">
          Id do Usuário
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
          textoDoBotao={"Procurar Usuario"}
          type={"submit"}
          className={"mt-3"}
        ></Button>
      </form>
    </SubPageContainer>
  );
};

export default FindUsuarioPage;
