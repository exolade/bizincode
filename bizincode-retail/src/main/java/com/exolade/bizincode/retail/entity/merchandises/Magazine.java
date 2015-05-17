package com.exolade.bizincode.retail.entity.merchandises;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.exolade.bizincode.retail.misc.MagazineCategory;


@Entity
@DiscriminatorValue(value = "Magazine")
@NamedQueries({
    @NamedQuery(name="Magazine.findAll",
                query="SELECT b FROM Magazine b"),
    @NamedQuery(name="Magazine.findByName",
    			query="SELECT b FROM Magazine b WHERE b.name = :name"),
    @NamedQuery(name="Magazine.findBySellBy",
    			query="SELECT b FROM Magazine b WHERE b.sell_by = :sell_by"),
    @NamedQuery(name="Magazine.findByCategoryAnimal",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.ANIMAL"),
    @NamedQuery(name="Magazine.findByCategoryArt",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.ART"),
    @NamedQuery(name="Magazine.findByCategoryAutomobile",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.AUTOMOBILE"),
    @NamedQuery(name="Magazine.findByCategoryBusiness",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.BUSINESS"),
    @NamedQuery(name="Magazine.findByCategoryEntertainment",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.ENTERTAINMENT"),
    @NamedQuery(name="Magazine.findByCategoryChildren",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.CHILDREN"),
    @NamedQuery(name="Magazine.findByCategoryTechnology",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.TECHNOLOGY"),
    @NamedQuery(name="Magazine.findByCategoryCraft",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.CRAFT"),
    @NamedQuery(name="Magazine.findByCategoryDesign",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.DESIGN"),
    @NamedQuery(name="Magazine.findByCategoryEducation",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.EDUCATION"),
    @NamedQuery(name="Magazine.findByCategoryFashion",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.FASHION"),
    @NamedQuery(name="Magazine.findByCategoryFood",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.FOOD"),
    @NamedQuery(name="Magazine.findByCategoryWINE",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.WINE"),
    @NamedQuery(name="Magazine.findByCategoryGame",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.GAME"),
    @NamedQuery(name="Magazine.findByCategoryHobby",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.HOBBY"),
    @NamedQuery(name="Magazine.findByCategoryHealth",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.HEALTH"),
    @NamedQuery(name="Magazine.findByCategorySports",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.SPORTS"),
    @NamedQuery(name="Magazine.findByCategoryScience",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.SCIENCE"),
    @NamedQuery(name="Magazine.findByCategoryNature",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.NATURE"),
    @NamedQuery(name="Magazine.findByCategoryTravel",
    			query="SELECT b FROM Magazine b WHERE b.category = com.exolade.bizincode.retail.misc.MagazineCategory.TRAVEL"),
})

public class Magazine extends PrintedProduct {
	private String name;
	private double price;
	private MagazineCategory category;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Enumerated(EnumType.STRING)
	public MagazineCategory getCategory() {
		return category;
	}
	public void setCategory(MagazineCategory category) {
		this.category = category;
	}
}
