$ ->
  $.get "/orders", (orderJson) ->
    $.each orderJson, (index, ord) ->
      id = $('<td>').append(ord.id)
      name = $('<td>').append(ord.product_name)
      brand = $('<td>').append(ord.product_brand)
      size = $('<td>').append(ord.product_size)
      qt = $('<td>').append(ord.product_quantity)

      #Append to table row
      row = $('<tr>').append(id, name, brand, size, qt)

      #Append to body
      $('#orders-container').append(row)