//Consultas estandarizadas por JPA y @Query personalizadas.

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // MÉTODOS POR DEFECTO: La consulta se genera automáticamente por el nombre del método.

    // 1. FIND + WHERE (equals)
    List<Empleado> findByDepartamento(String departamento);
    
    // 2. FIND + WHERE (diferentes operadores)
    List<Empleado> findBySalarioGreaterThan(Double salario);
    List<Empleado> findBySalarioLessThanEqual(Double salario);
    List<Empleado> findByEdadBetween(int inicio, int fin);
    
    // 3. FIND + LIKE
    List<Empleado> findByNombreContaining(String texto);
    List<Empleado> findByNombreStartingWith(String prefijo);
    List<Empleado> findByNombreEndingWith(String sufijo);
    
    // 4. FIND + AND / OR
    List<Empleado> findByDepartamentoAndSalarioGreaterThan(String depto, Double salario);
    List<Empleado> findByDepartamentoOrCargo(String depto, String cargo);
    
    // 5. FIND + ORDER BY (ASC / DESC)
    List<Empleado> findByDepartamentoOrderBySalarioAsc(String depto);
    List<Empleado> findByDepartamentoOrderBySalarioDesc(String depto);
    List<Empleado> findByActivoTrueOrderByFechaIngresoDesc();
    
    // 6. COUNT - Contar registros
    long countByDepartamento(String departamento);
    long countBySalarioGreaterThan(Double salario);
    long countByActivoTrue();
    
    // 7. DISTINCT
    List<Empleado> findDistinctByDepartamento(String departamento);
    List<String> findDistinctDepartamentoBy();
    
    // 8. EXISTS
    boolean existsByNombre(String nombre);
    boolean existsByDepartamentoAndActivo(String depto, boolean activo);
    
    // 9. DELETE
    void deleteByDepartamento(String departamento);
    long deleteBySalarioLessThan(Double salario);
    
    // 10. TOP / FIRST / LIMIT
    List<Empleado> findTop3ByOrderBySalarioDesc();
    Empleado findFirstByOrderBySalarioDesc();
    List<Empleado> findTop5ByDepartamentoOrderByFechaIngresoAsc(String depto);
    
    // 11. NULL handling
    List<Empleado> findByJefeIsNull();
    List<Empleado> findByEmailIsNotNull();
    
    // 12. IN
    List<Empleado> findByDepartamentoIn(List<String> departamentos);
    
    // 13. IGNORE CASE (insensible a mayúsculas)
    List<Empleado> findByNombreIgnoreCase(String nombre);
    List<Empleado> findByDepartamentoIgnoreCaseOrderByNombreAsc(String depto);
    
    // 14. Combinaciones complejas con AND/OR + ORDER BY
    List<Empleado> findByDepartamentoAndSalarioBetweenOrderBySalarioDesc(String depto, Double min, Double max);
    
    // 15. COUNT + DISTINCT (implícito)
    long countDistinctByDepartamento(String departamento);
    
    // 16. FIND + NOT
    List<Empleado> findByDepartamentoNot(String departamento);
    List<Empleado> findByActivoFalse();
    
    // 17. FIND + AFTER / BEFORE (para fechas)
    List<Empleado> findByFechaIngresoAfter(LocalDate fecha);
    List<Empleado> findByFechaIngresoBefore(LocalDate fecha);
    
    // 18. FIND + IS EMPTY (para colecciones)
    List<Empleado> findByProyectosIsEmpty();
    List<Empleado> findByProyectosIsNotEmpty();
    
    // 19. FIND + TRUE / FALSE
    List<Empleado> findByActivoTrue();
    List<Empleado> findByActivoFalse();
    
    // 20. Combinación: LIKE + ORDER BY múltiple
    List<Empleado> findByNombreContainingIgnoreCaseOrderByDepartamentoAscSalarioDesc(String texto);


    // MÉTODOS PERSONALIZADOS CON @Query (JPQL y SQL nativo)

    // 1. SELECT + WHERE básico
    @Query("SELECT e FROM Empleado e WHERE e.departamento = :departamento")
    List<Empleado> buscarPorDepartamento(@Param("departamento") String depto);

    // 2. COUNT - Contar registros
    @Query("SELECT COUNT(e) FROM Empleado e WHERE e.activo = true")
    long contarActivos();

    // 3. DISTINCT - Evitar duplicados
    @Query("SELECT DISTINCT e.departamento FROM Empleado e")
    List<String> findDepartamentosUnicos();

    // 4. CONCAT - Concatenar campos
    @Query("SELECT CONCAT(e.nombre, ' ', e.apellido) FROM Empleado e")
    List<String> obtenerNombresCompletos();

    // 5. MAX - Valor máximo
    @Query("SELECT MAX(e.salario) FROM Empleado e")
    Double obtenerSalarioMaximo();

    // 6. MIN - Valor mínimo
    @Query("SELECT MIN(e.salario) FROM Empleado e")
    Double obtenerSalarioMinimo();

    // 7. SUM - Sumar valores
    @Query("SELECT SUM(e.salario) FROM Empleado e WHERE e.departamento = :depto")
    Double sumarSalariosPorDepartamento(@Param("depto") String departamento);

    // 8. AVG - Promedio
    @Query("SELECT AVG(e.salario) FROM Empleado e")
    Double obtenerSalarioPromedio();

    // 9. ORDER BY ASC - Orden ascendente
    @Query("SELECT e FROM Empleado e ORDER BY e.salario ASC")
    List<Empleado> ordenarPorSalarioAsc();

    // 10. ORDER BY DESC - Orden descendente
    @Query("SELECT e FROM Empleado e ORDER BY e.fechaIngreso DESC")
    List<Empleado> ordenarPorFechaIngresoDesc();

    // 11. COUNT + WHERE + GROUP BY + ORDER BY DESC
    @Query("SELECT e.departamento, COUNT(e) FROM Empleado e WHERE e.activo = true GROUP BY e.departamento ORDER BY COUNT(e) DESC")
    List<Object[]> contarEmpleadosActivosPorDeptoDesc();

    // 12. MAX + GROUP BY + ORDER BY DESC
    @Query("SELECT e.departamento, MAX(e.salario) FROM Empleado e GROUP BY e.departamento ORDER BY MAX(e.salario) DESC")
    List<Object[]> salarioMaximoPorDeptoDesc();

    // 13. AVG + WHERE + DISTINCT + GROUP BY
    @Query("SELECT DISTINCT e.departamento, AVG(e.salario) FROM Empleado e WHERE e.edad > :edadMinima GROUP BY e.departamento")
    List<Object[]> promedioSalarioPorDeptoMayoresDe(@Param("edadMinima") int edad);

    // 14. CONCAT + WHERE + ORDER BY ASC
    @Query("SELECT CONCAT(e.nombre, ' (', e.departamento, ')') FROM Empleado e WHERE e.salario > :salarioMin ORDER BY e.nombre ASC")
    List<String> listarNombresConDeptoSueldoMayorA(@Param("salarioMin") double salarioMin);

    // 15. SELECT + WHERE + ORDER BY mezclado (ASC y DESC en diferentes campos)
    @Query("SELECT e FROM Empleado e WHERE e.activo = true ORDER BY e.departamento ASC, e.salario DESC")
    List<Empleado> activosPorDeptoAscSalarioDesc();

    // 16. COUNT + DISTINCT + WHERE
    @Query("SELECT COUNT(DISTINCT e.departamento) FROM Empleado e WHERE e.salario > :salarioMin")
    Long contarDepartamentosConSalarioMayorA(@Param("salarioMin") double salarioMin);

    // 17. SUM + WHERE + ORDER BY (mezcla de agregación con orden)
    @Query("SELECT e.departamento, SUM(e.salario) FROM Empleado e WHERE e.activo = true GROUP BY e.departamento ORDER BY SUM(e.salario) DESC")
    List<Object[]> sumaSalariosPorDeptoActivosDesc();

    // 18. MAX + MIN + misma consulta (usando dos proyecciones)
    @Query("SELECT MAX(e.salario), MIN(e.salario), AVG(e.salario) FROM Empleado e WHERE e.departamento = :depto")
    List<Object[]> estadisticasSalarialesPorDepto(@Param("depto") String departamento);

    // 19. CONCAT + DISTINCT + ORDER BY DESC
    @Query("SELECT DISTINCT CONCAT(e.departamento, ' - ', UPPER(e.cargo)) FROM Empleado e ORDER BY e.departamento DESC")
    List<String> listarDeptoCargoUnicoDesc();

    // 20. Consulta nativa (SQL puro) con todo mezclado
    @Query(value = "SELECT departamento, COUNT(*), AVG(salario), MAX(salario), MIN(salario) FROM empleados WHERE activo = 1 GROUP BY departamento ORDER BY COUNT(*) DESC", nativeQuery = true)
    List<Object[]> reporteCompletoPorDeptoNativo();
}