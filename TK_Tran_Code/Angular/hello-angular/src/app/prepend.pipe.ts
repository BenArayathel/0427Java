import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
	name: 'prepend'
})
export class PrependPipe implements PipeTransform {

	transform(param1: string, param2: string): unknown {
		// we can have super complex logic here..
		return param2.concat(param1);
	}

}
