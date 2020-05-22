window.onload = function() {
    this.document.addEventListener('DOMContentLoaded', function(e) {
        const fv = FormValidation.formValidation(
            document.getElementById('demoForm'),
            {
                fields: {
                    name: {
                        validators: {
                            notEmpty: {
                                message: 'The name is required'
                            }
                        }
                    },
                    date: {
                        validators: {
                            notEmpty: {
                                message: 'The date is required'
                            },
                            date: {
                                format: 'MM/DD/YYYY',
                                min: '01/01/2010',
                                max: '12/30/2020',
                                message: 'The date is not valid'
                            }
                        }
                    },
                },
                plugins: {
                    trigger: new FormValidation.plugins.Trigger(),
                    bootstrap: new FormValidation.plugins.Bootstrap(),
                    submitButton: new FormValidation.plugins.SubmitButton(),
                    icon: new FormValidation.plugins.Icon({
                        valid: 'fa fa-check',
                        invalid: 'fa fa-times',
                        validating: 'fa fa-refresh',
                    }),
                },
            }
        );
    
        $('[name="date"]')
            .datepicker({
                format: 'mm/dd/yyyy',
                startDate: '01/01/2010',
                endDate: '12/30/2020',
            })
            .on('changeDate', function(e) {
                // Revalidate the date field
                fv.revalidateField('date');
            });
    });
}