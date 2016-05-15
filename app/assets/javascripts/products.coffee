#View Inventory
$ ->  
  $.get "/products", (productJson) ->
    $.each productJson, (index, prod) ->
      id = $('<td>').append(prod.id)
      name = $('<td>').append(prod.name)
      brand = $('<td>').append(prod.brand)
      price = $('<td>').append(prod.price)
      size = $('<td>').append(prod.size)
      kind = $('<td>').append(prod.kind)
      qt = $('<td>').append(prod.quantity)
      perct = $('<td>').append(prod.percentage)
      origin = $('<td>').append(prod.origin)
      status = $('<td>').append(prod.status)

      #Append to table row
      row = $('<tr>').append(id, name, brand, price, size, kind, qt, perct, origin, status)

      #Append to body
      $('#product-container').append(row)

