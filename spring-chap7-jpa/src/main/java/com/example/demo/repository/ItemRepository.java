package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Item;

//「Jparepository<Entityクラス、@Idをつけたフィールドのデータ型>」を継承
public interface ItemRepository extends JpaRepository<Item, Integer>{
	
	//全件取得以外のSELECT文は、repository内に記述する
	//※itemsテーブルはitemRepository、categoryテーブルはcategoryRepositoryに記述
	@Query(nativeQuery = true,
			value = """
					SELECT*
					FROM items
					WHERE category_id = :categoryId
					"""
			)
	List<Item>findByCategoryId(Integer categoryId);

}
