module.exports = function(grunt) {

    grunt.initConfig({

    less: {
        development: {
            files: {
            'build/styles/main.css': 'source/styles/main.less'  
            }
        }
    },

        uglify: {
            my_target: {
            files: {
            'build/scripts/scripts.min.js': ['source/scripts/scripts.js']  
            }
        }
    }

    });

    
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-uglify');

    
    grunt.registerTask('default', ['less', 'uglify']);

};
