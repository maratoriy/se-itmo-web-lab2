const languageForm = document.querySelector("#change-language-form");
languageForm.addEventListener("change", () => {
    const language = formDataToObject(new FormData(languageForm)).language.split("_")
    console.log(language)
    post_request(`${contextPath}`, () => window.location.reload(), {function: "changeLocale", language: language[0], country: language[1]})
})