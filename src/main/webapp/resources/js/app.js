document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  const bags = document.getElementsByName("bags_declared").item(0);
  const categories = document.getElementById("categories_declared");
  const institution = document.getElementById("institution_declared");
  const street = document.getElementById("street_declared");
  const city = document.getElementById("city_declared");
  const zipCode = document.getElementById("zipCode_declared");
  const pickUpDate = document.getElementById("pickUpDate_declared");
  const pickUpTime = document.getElementById("pickUpTime_declared");
  const pickUpComment = document.getElementById("pickUpComment_declared");

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }


    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary - almost done

      bags.textContent=document.querySelector("#bags").value;
      var selected_categories = document.querySelectorAll("#category");
      var selected_institutions =  document.querySelectorAll("#institution");
      street.textContent=document.querySelector("#street").value;
      city.textContent=document.querySelector("#city").value;
      zipCode.textContent=document.querySelector("#zipCode").value;
      pickUpDate.textContent=document.querySelector("#pickUpDate").value;
      pickUpTime.textContent=document.querySelector("#pickUpTime").value;
      pickUpComment.textContent=document.querySelector("#pickUpComment").value;

      selected_institutions.forEach((element) => {
        if (element.checked === true) {
          institution.textContent = element.dataset.name;
        }
      })

      selected_categories.forEach((element) => {
          if (this.currentStep === 5) {
              if (element.checked === true) {
                  if (categories.textContent === "") {
                      categories.textContent = element.dataset.name;
                  } else {
                      categories.textContent = categories.textContent + ", " + element.dataset.name;
                  }
              }
          } else {
              categories.textContent = "";
          }
      })
    }

  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }

  const hidden_categories = document.getElementsByName("_categories");
  const hidden_institutions = document.getElementsByName("_institutions");

  window.addEventListener("load", function() {
    for (var i = hidden_categories.length - 1; i >= 0; i--) {
      hidden_categories[i].remove();
    }
  });
  window.addEventListener("load", function() {
    for (var i = hidden_institutions.length - 1; i >= 0; i--) {
      hidden_institutions[i].remove();
    }
  });

  var passwordTyped = document.getElementsByName("password")[0]
  var passwordRetyped = document.getElementsByName("password2")[0]
  function passwordsUnmatchedError (passwordWindow1, passwordWindow2) {
    errorDiv = document.createElement("div");
    errorDiv.innerText = "Hasła nie są takie same.";
    // passwordWindow1.parentNode.appendChild(errorDiv);
    if (passwordWindow2.parentNode.childElementCount > 1) {
      passwordWindow2.parentNode.lastElementChild.remove()
    }
    passwordWindow2.parentNode.appendChild(errorDiv);
  }

  console.log(passwordTyped)
  console.log(passwordRetyped)

  passwordRetyped.addEventListener("blur", function() {
    if (passwordTyped.value !== passwordRetyped.value) {
      passwordsUnmatchedError(passwordTyped, passwordRetyped);
    }
  })

  passwordRetyped.addEventListener("keyup", function() {
    if (passwordRetyped.parentNode.childElementCount > 1) {
      passwordRetyped.parentNode.lastElementChild.remove()
    }
  })

});
