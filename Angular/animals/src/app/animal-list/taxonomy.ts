export class Taxonomy extends Map<string,string>{

    constructor(taxonomy?:object){
        super();
        if (taxonomy) {
            for (const taxCat in taxonomy) {
                this.set(taxCat, taxonomy[taxCat])
            }
        }
    }

    sortOrder = (t1:string, t2:string) => {
        return 0;
    }
}