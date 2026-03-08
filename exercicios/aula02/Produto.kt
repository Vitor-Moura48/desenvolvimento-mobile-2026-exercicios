fun main() {
    
    val p1 = Produto("Notebook", 3500.0, 10)
    val p2 = Produto("Mouse Gamer", 150.0, 50)
    val p3 = Produto("Teclado Mecânico", 300.0, 20)
    val p4 = Produto("Monitor 4K", 1200.0, 5)
    val p5 = Produto("Headset", 250.0, 15)

    val listaProdutos = listOf(p1, p2, p3, p4, p5)
    
    p1.exibirInfo()
    p1.vender(4)
    p1.exibirInfo()
    println("--------------------------------------------------------------------")

    p2.exibirInfo()
    p2.vender(60)
    println("--------------------------------------------------------------------")

    p3.exibirInfo()
    p3.repor(30)
    p3.exibirInfo()
    println("--------------------------------------------------------------------")

    p4.exibirInfo()
    p4.vender(-5)
    p4.repor(0)
    println("--------------------------------------------------------------------")

    p5.exibirInfo()
    p5.vender(10)
    p5.repor(20)
    p5.vender(25)
    println("--------------------------------------------------------------------")
    
    println("\n========================================================================\n")
    listaProdutos.forEach { it.exibirInfo() }
    println("\n========================================================================\n")
}

class Produto (nome: String, preco: Double, estoque: Int){
    
    val nome: String = nome
    private var preco: Double = preco
    private var estoque: Int = estoque
    
	fun vender(quantidade: Int){
        
        if (quantidade <= 0){
            println("Quantidade deve ser maior que zero.")
            return
        }	
        
        if (quantidade <= estoque){
            estoque -= quantidade
          	
            if (quantidade == 1) 
            	println("Produto vendido.")
            else 
            	println("${quantidade} produtos vendidos.")
        } else 
        	println("Quantidade excede o estoque atual (${estoque}).")
    }
    
    fun repor(quantidade: Int){
        
        if (quantidade <= 0){
            println("Quantidade deve ser maior que zero.")
            return
        }
        	
        estoque += quantidade
        println("Estoque reposto.")
    }
    
    fun exibirInfo(){
        println("Produto: ${nome.padEnd(18)} | Preço: R$ ${"%.2f".format(preco).padEnd(12)} | Estoque: $estoque")
    }
}