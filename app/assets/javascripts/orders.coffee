$ ->
  $.get "/orders", (orderJson) ->
    $.each orderJson, (index, ord) ->
      id = $('<td>').append(ord.id)
      name = $('<td>').append(ord.name)
      brand = $('<td>').append(ord.brand)
      size = $('<td>').append(ord.size)
      qt = $('<td>').append(ord.quantity)

      #Append to table row
      row = $('<tr>').append(id, name, brand, size, qt)

      #Append to body
      $('#orders-container').append(row)