$ ->
  $.get "/users/display", (users) ->
    $.each users, (index, user) ->
      fn = $("<div>").addClass("first_name").text user.firstName
      ln = $("<div>").addClass("last_name").text user.lastName
      $("#users").append $("<li>").append(fn).append(ln)