const languageForm = document.querySelector("#change-language-form");
languageForm.addEventListener("change", () => {
    const languageTag = formDataToObject(new FormData(languageForm)).language;
    console.log(languageTag)
    post_request(`${contextPath}`, () => window.location.reload(), {function: "changeLocale", languageTag})
})