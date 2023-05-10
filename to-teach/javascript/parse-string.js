function expenses(allExpenses) {
  const rows = allExpenses.split('\n')

  const departments = []

  rows.shift()
  let departmentName = ''
  let totalAmount = 0
  for (let i = 0; i < rows.length; i++) {
    if (departmentName === '') {
      departmentName = rows[i]
      continue
    }

    if (rows[i] === '') {
      departments.push({
        name: departmentName,
        total: totalAmount
      })
      departmentName = ''
      totalAmount = 0
      continue
    }

    totalAmount += parseInt(rows[i], 10)
  }

  if (departments.length == 0) {
    return []
  }

  let maxDepartment = departments[0]
  let minDepartment = departments[0]
  for (let i = 1; i < departments.length; i++) {
    if (departments[i].total < minDepartment.total) {
      minDepartment = departments[i]
    }
    if (departments[i].total > maxDepartment.total) {
      maxDepartment = departments[i]
    }
  }

  return [maxDepartment, minDepartment]
}

console.log(expenses(`
Reparto 1
1
2
3

Reparto 2
0

Reparto 3
9
`))
