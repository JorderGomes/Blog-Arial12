/* eslint-disable @next/next/no-async-client-component */
"use client";

import Button from "@/components/Button";
import SubPageContainer from "@/components/SubPageContainer";
import { useRouter } from "next/navigation";
import { useState } from "react";

const FindPostPage = () => {
  const [id, setId] = useState(0);

  const router = useRouter();

  async function submitHandler(event) {
    event.preventDefault();
    router.push(`/posts/${id}`);
  }

  function handleChange(event) {
    const { value } = event.target;
    setId(value);
  }

  return (
    <SubPageContainer
      menuTitle="Buscar Post por Id:"
      returnCallback={() => router.push("/posts")}
    >
      <form
        onSubmit={submitHandler}
        style={{ display: "flex", flexDirection: "column" }}
      >
        <label className="form-label" htmlFor="id">
          Id do post
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
          textoDoBotao={"Procurar Post"}
          type={"submit"}
          className={"mt-3"}
        ></Button>
      </form>
    </SubPageContainer>
  );
};

export default FindPostPage;
